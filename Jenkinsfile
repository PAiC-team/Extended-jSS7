pipeline {
	agent any

	tools {
	    jdk 'JDK 11'
		maven 'maven3.6.1'
	}
	parameters {
	    string(name: 'jSS7_MAJOR_VERSION_NUMBER', defaultValue: '8.3.0', description: 'The major version for Extended jSS7')
	    string(name: 'SCTP_MAJOR_VERSION_NUMBER', defaultValue: '2.0.2', description: 'The major version of Extended SCTP for Extended jSS7')
	    string(name: 'SCTP_BUILD', defaultValue: '13', description: 'The build number of Extended SCTP for Extended jSS7 to use for the build')
	    // booleanParam(name: 'BUILD_ANT', defaultValue: true, description: 'Enable if Binary needs to be generated')
	}

	stages {
		stage('Set Version') {
			steps {
			    script {
                    if (BUILD_NUMBER == "1") {
                        error "Building for the first time"
                    }
                }
				sh "mvn versions:set -DnewVersion=${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER}"
				echo "Setting version to ${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER} completed"
			}
		}

		stage("Build") {
			steps {
				script {
                    EXTENDED_SCTP_VERSION = "${params.SCTP_MAJOR_VERSION_NUMBER}-${params.SCTP_BUILD}"
                    currentBuild.displayName = "#${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER}"
                    currentBuild.description = "PAiC Extended jSS7 build"
                }
				echo "Building Extended jSS7 version (#${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER})"
				sh "mvn clean install -Dsctp.version=${EXTENDED_SCTP_VERSION} -Dss7.restcomm.version=${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER} -DskipTests"
				echo "Maven build completed."
			}
		}

		stage("Ant") {
		    // when { expression { params.BUILD_ANT.toBoolean() == true}}
			steps {
                script {
                    EXTENDED_SCTP_VERSION = "${params.SCTP_MAJOR_VERSION_NUMBER}-${params.SCTP_BUILD}"
                }
				echo "Starting ant build for version #${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER}"
				withAnt(installation: 'Ant1.10') {
 				   dir('release'){
 				      sh "ant -f build.xml -Drelease.version=${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER} -Dsctp.version=${EXTENDED_SCTP_VERSION}"
 				   }
				}
			}
		}
		stage('Save Artifacts') {
            steps {
                echo "Archiving Extended-jSS7-${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER}"
                archiveArtifacts artifacts: "release/Extended-jSS7-${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER}.zip", followSymlinks: false, onlyIfSuccessful: true
            }
        }
        stage('Push to Repo') {
            when { anyOf { branch 'master'; branch 'release' } }
		    steps {
			    sshagent(['ssh_grafana']) {
				    sh "scp release/Extended-jSS7-${params.jSS7_MAJOR_VERSION_NUMBER}-${BUILD_NUMBER}.zip root@45.79.17.57:/var/www/html/PAIC_Extended/jss7/"
	  	        }
		    }
	    }

	    stage('Push to jFrog') {
	        when {anyOf {branch 'master'; branch 'release'}}
	        steps {
	            sh 'mvn deploy -DskipTests'
	        }
	    }
	}

	post {
		success {
			echo "Successfully build"
		}
        failure{
            script{
                currentBuild.description = "Extended jSS7 build - failed"
            }
        }
		always {
			echo "This will be called always. After testing do clean up"
		}
	}
}