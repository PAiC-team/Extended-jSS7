package org.restcomm.protocols.ss7.isup.impl.message.parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.restcomm.protocols.ss7.isup.ParameterException;
import org.restcomm.protocols.ss7.isup.message.parameter.Information;

/**
 * @author baranowb
 *
 */
abstract class AbstractInformationParameterBaseImpl extends AbstractISUPParameter{

    private List<Information> infos = new ArrayList<Information>();

    public int decode(byte[] b) throws ParameterException {
        if (b.length < 1) {
            throw new ParameterException();
        }

        int index = 0;
        for (;;) {
            byte tag = b[index++];
            byte len = b[index++];
            byte[] value = new byte[len];
            System.arraycopy(b, index, value, 0, len);
            index += len;

            Information info = initializeInformation(tag,value);
            this.infos.add(info);
            if (index >= b.length) {
                break;
            }
        }
        return b.length;
    }

    public byte[] encode() throws ParameterException {
        if (this.infos.size() == 0) {
            throw new ParameterException();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (Information info : this.infos) {
            baos.write(info.getTag());
            final byte[] value = ((AbstractInformationImpl)info).encode();
            baos.write(value.length);
            try {
                baos.write(value);
            } catch (IOException e) {
                throw new ParameterException(e);
            }
        }
        return baos.toByteArray();
    }

    protected void setInformation(Information... infos) {

        if (infos == null || infos.length == 0) {
            return;
        }
        Class cellClass = infos.getClass().getComponentType();
        for(int index = 0;index<this.infos.size();index++){
            if(cellClass.isAssignableFrom(this.infos.get(index).getClass())){
                this.infos.remove(index);
                index--;
            }
        }
        for (Information i : infos) {
            if (i == null) {
                continue;
            }
            this.infos.add(i);
        }
    }
    protected Information[] getInformation(Class<? extends Information> targetClass){
        List<Information> target = new ArrayList<Information>();
        for(Information i:this.infos){
            if(targetClass.isAssignableFrom(i.getClass()))
                target.add(i);
        }
        return target.toArray((Information[])Array.newInstance(targetClass,target.size()));
    }
    protected abstract Map<Integer, Class<? extends AbstractInformationImpl>> getTagMapping();

    protected AbstractInformationImpl initializeInformation(final int tag, final byte[] data) throws ParameterException{
        final int tagStripped = tag & 0xFF;
        final Map<Integer, Class<? extends AbstractInformationImpl>> tagMapping = getTagMapping();
        Class<? extends AbstractInformationImpl> clazz = tagMapping.get(tagStripped);
        if(clazz == null){
            throw new ParameterException("No registered information for tag: "+tagStripped);
        }
        try {
            AbstractInformationImpl info = clazz.newInstance();
            info.setTag(tagStripped);
            info.decode(data);
            return info;
        } catch (Exception e) {
            throw new ParameterException(e);
        }
    }
}
