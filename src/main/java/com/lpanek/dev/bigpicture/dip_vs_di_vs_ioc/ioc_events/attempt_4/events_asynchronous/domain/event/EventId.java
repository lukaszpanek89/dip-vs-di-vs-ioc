package com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.ioc_events.attempt_4.events_asynchronous.domain.event;

import com.google.common.base.Objects;
import com.lpanek.dev.bigpicture.dip_vs_di_vs_ioc.util.Immutable;
import java.util.UUID;

@Immutable
public final class EventId {

	private final String id;

	EventId() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		EventId that = (EventId) o;
		return Objects.equal(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return id;
	}
}
