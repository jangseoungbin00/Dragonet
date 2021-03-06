/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.net.packet.minecraft;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.dragonet.utilities.io.PEBinaryWriter;

public class MoveEntitiesPacket extends PEPacket {

    public MoveEntityData[] entities;

    public MoveEntitiesPacket(MoveEntityData[] entities) {
        this.entities = entities;
    }

    @Override
    public int pid() {
        return PEPacketIDs.MOVE_ENTITY_PACKET;
    }

    @Override
    public void encode() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PEBinaryWriter writer = new PEBinaryWriter(bos);
            writer.writeByte((byte) (this.pid() & 0xFF));
            writer.writeInt(this.entities.length);
            for (MoveEntityData d : this.entities) {
                writer.writeInt(d.eid);
                writer.writeFloat(d.x);
                writer.writeFloat(d.y);
                writer.writeFloat(d.z);
                writer.writeFloat(d.yaw);
                writer.writeFloat(d.pitch);
            }
            this.setData(bos.toByteArray());
        } catch (IOException e) {
        }
    }

    @Override
    public void decode() {
    }

    public static class MoveEntityData {

        public int eid;
        public float x;
        public float y;
        public float z;
        public float yaw;
        public float pitch;
    }

}
