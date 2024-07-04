package io.lokopay.model;

import com.google.gson.annotations.SerializedName;
import io.lokopay.exception.LokoException;
import io.lokopay.net.ApiResource;
import io.lokopay.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Customer extends ApiResource implements HasId, MetadataStore<Customer> {

//    @Getter(onMethod_ = {@Override})
    @SerializedName("id")
    String id;

    @SerializedName("ip_address")
    String ipAddress;

    @SerializedName("email")
    String email;

    @SerializedName("destination_address")
    String destinationAddress;

    @SerializedName("destination_network")
    String destinationNetwork;

    @Getter(onMethod_ = {@Override})
    @SerializedName("metadata")
    Map<String, String> metadata;

    @Override
    public Customer update(Map<String, Object> params) throws LokoException {
        return null;
    }

    @Override
    public Customer update(Map<String, Object> params, RequestOptions options) throws LokoException {
        return null;
    }
}
