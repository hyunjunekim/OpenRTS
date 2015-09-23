package brainless.openrts.event.network;

import com.jme3.network.serializing.Serializable;


@Serializable
public class MoveAttackEvent extends NetworkEvent {

	private long entityId;

	public MoveAttackEvent(long entityId) {
		this.entityId = entityId;
	}

}