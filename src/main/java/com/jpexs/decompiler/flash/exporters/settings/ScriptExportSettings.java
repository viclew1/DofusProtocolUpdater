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
 * License along with this library.
 */
package com.jpexs.decompiler.flash.exporters.settings;

import com.jpexs.decompiler.flash.exporters.modes.ScriptExportMode;

/**
 * @author JPEXS
 */
public class ScriptExportSettings {

    public ScriptExportMode mode;

    public ScriptExportSettings(ScriptExportMode mode) {
        this.mode = mode;
    }

    public String getFileExtension() {
        switch (this.mode) {
            case AS:
            case AS_METHOD_STUBS:
                return ".as";
            case PCODE_GRAPHVIZ:
                return ".gv";
            case PCODE:
            case PCODE_HEX:
                return ".pcode";
            case HEX:
                return ".hex";
            case CONSTANTS:
                return ".txt";
            default:
                throw new Error("Unsupported script export mode: " + this.mode);
        }
    }
}
