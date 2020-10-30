/*
 * Copyright 2017-2020 Volkan Yazıcı
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permits and
 * limitations under the License.
 */

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
