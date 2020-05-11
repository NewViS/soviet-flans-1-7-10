package com.flansmod.common.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.EntityDriveable;
import com.flansmod.common.driveables.EntityVehicle;

public class PacketVehicleControl extends PacketDriveableControl 
{
	public boolean doors;
	public float floatred;
	public float floatgreen;
	public float floatblue;
	//public String disk_path1;
	//public String disk_texture_path1;
	
	public PacketVehicleControl() {}

	public PacketVehicleControl(EntityDriveable driveable) 
	{
		super(driveable);
		EntityVehicle vehicle = (EntityVehicle)driveable;
		DriveableData data = vehicle.getDriveableData();
		doors = vehicle.varDoor;
		floatred = data.fr;
		floatgreen = data.fg;
		floatblue = data.fb;
		//disk_path1 = vehicle.disk_path;
		//disk_texture_path1 = vehicle.disk_texture_path;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf data) 
	{
    	super.encodeInto(ctx, data);
    	data.writeBoolean(doors);
    	data.writeFloat(floatred);
    	data.writeFloat(floatgreen);
    	data.writeFloat(floatblue);
    	//writeUTF(data, disk_path1);
    	//writeUTF(data, disk_texture_path1);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf data) 
	{
		super.decodeInto(ctx, data);
		doors = data.readBoolean();
		floatred = data.readFloat();
		floatgreen = data.readFloat();
		floatblue = data.readFloat();
		//disk_path1 = readUTF(data);
		//disk_texture_path1 = readUTF(data);
	}
	
	@Override
	protected void updateDriveable(EntityDriveable driveable, boolean clientSide)
	{
		super.updateDriveable(driveable, clientSide);
		EntityVehicle vehicle = (EntityVehicle)driveable;
		vehicle.varDoor = doors;
		DriveableData data = vehicle.getDriveableData();
		data.fr = floatred;
		data.fg = floatgreen;
		data.fb = floatblue;
		//vehicle.disk_path = disk_path1;
		//vehicle.disk_texture_path = disk_texture_path1;
	}
}
