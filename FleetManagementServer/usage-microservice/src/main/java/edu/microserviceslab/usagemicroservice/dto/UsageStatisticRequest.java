package edu.microserviceslab.usagemicroservice.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class UsageStatisticRequest {
    private Long vehicleId;
    private List<PidRequest> pids;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public List<PidRequest> getPids() {
        return pids;
    }

    public void setData(List<PidRequest> pids) {
        this.pids = pids;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UsageStatisticRequest that = (UsageStatisticRequest) o;

        return new EqualsBuilder()
                .append(vehicleId, that.vehicleId)
                // ignoring pids for now
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(vehicleId)
                // ignoring pids for now
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("vehicleId", vehicleId)
                // ignoring pids for now
                .toString();
    }
}
