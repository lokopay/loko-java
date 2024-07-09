package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiResource;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class NetworkFee extends ApiResource {

    @SerializedName("object")
    String object;

    @SerializedName("destination_network_details")
    List<BlockchainNetwork> destinationNetworkDetails;
}
