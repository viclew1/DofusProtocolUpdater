/*
 *  Copyright (C) 2010-2021 JPEXS, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. */
package com.jpexs.decompiler.flash.tags.gfx;

import com.jpexs.decompiler.flash.SWFInputStream;
import com.jpexs.decompiler.flash.SWFOutputStream;
import com.jpexs.decompiler.flash.tags.Tag;
import com.jpexs.helpers.ByteArrayRange;
import java.io.IOException;

/**
 *
 * @author JPEXS
 */
public class DefineExternalImage2 extends Tag {

    public static final int ID = 1009;

    public static final String NAME = "DefineExternalImage2";

    public long characterId;

    public int bitmapFormat;

    public int targetWidth;

    public int targetHeight;

    public String exportName;

    public String fileName;

    public byte[] extraData; //?

    public static final int BITMAP_FORMAT_DEFAULT = 0;

    public static final int BITMAP_FORMAT_TGA = 1;

    public static final int BITMAP_FORMAT_DDS = 2;

    /**
     * Gets data bytes
     *
     * @param sos SWF output stream
     * @throws java.io.IOException
     */
    @Override
    public void getData(SWFOutputStream sos) throws IOException {
        sos.writeUI32(characterId);
        sos.writeUI16(bitmapFormat);
        sos.writeUI16(targetWidth);
        sos.writeUI16(targetHeight);
        sos.writeNetString(exportName);
        sos.writeNetString(fileName);
        if (extraData != null) {
            sos.write(extraData);
        }
    }

    /**
     * Constructor
     *
     * @param sis
     * @param data
     * @throws IOException
     */
    public DefineExternalImage2(SWFInputStream sis, ByteArrayRange data) throws IOException {
        super(sis.getSwf(), ID, NAME, data);
        readData(sis, data, 0, false, false, false);
    }

    @Override
    public final void readData(SWFInputStream sis, ByteArrayRange data, int level, boolean parallel, boolean skipUnusualTags, boolean lazy) throws IOException {
        characterId = sis.readUI32("characterId");
        bitmapFormat = sis.readUI16("bitmapFormat");
        targetWidth = sis.readUI16("targetWidth");
        targetHeight = sis.readUI16("targetHeight");
        exportName = sis.readNetString("exportName");
        fileName = sis.readNetString("fileName");
        if (sis.available() > 0) { //there is usually one zero byte, bod knows why
            extraData = sis.readBytesEx(sis.available(), "extraData");
        }
    }
}
