package com.vlkan.log4j2.logstash.layout.util;

import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class ByteBufferOutputStream extends OutputStream {

    private final ByteBuffer byteBuffer;

    public ByteBufferOutputStream(int byteCount) {
        this.byteBuffer = ByteBuffer.allocate(byteCount);
    }

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }

    @Override
    public void write(int codeInt) {
        byte codeByte = (byte) codeInt;
        byteBuffer.put(codeByte);
    }

    @Override
    public void write(byte[] buf) {
        byteBuffer.put(buf);
    }

    @Override
    public void write(byte[] buf, int off, int len) {
        byteBuffer.put(buf, off, len);
    }

    public byte[] toByteArray() {
        @SuppressWarnings("RedundantCast")  // for Java 8 compatibility
        int size = ((Buffer) byteBuffer).position();
        byte[] buffer = new byte[size];
        System.arraycopy(byteBuffer.array(), 0, buffer, 0, size);
        return buffer;
    }

    public String toString(Charset charset) {
        // noinspection RedundantCast (for Java 8 compatibility)
        return new String(byteBuffer.array(), 0, ((Buffer) byteBuffer).position(), charset);
    }

}
