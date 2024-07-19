package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiRequestParams;

public class ListParams extends ApiRequestParams {

    @SerializedName("limit")
    private Long limit;

    @SerializedName("created_from")
    private Long createdFrom;

    @SerializedName("created_to")
    private Long createdTo;

    @SerializedName("ending_before")
    private String endingBefore;

    @SerializedName("starting_after")
    private String startingAfter;

    private ListParams(
            Long limit,
            Long createdFrom,
            Long createdTo,
            String endingBefore,
            String startingAfter
    ) {
        this.limit = limit;
        this.createdFrom = createdFrom;
        this.createdTo = createdTo;
        this.endingBefore = endingBefore;
        this.startingAfter = startingAfter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long limit;

        private Long createdFrom;

        private Long createdTo;

        private String endingBefore;

        private String startingAfter;

        public ListParams build() {
            return new ListParams(
                    this.limit,
                    this.createdFrom,
                    this.createdTo,
                    this.endingBefore,
                    this.startingAfter
            );
        }

        public Builder setLimit(Long limit) {
            this.limit = limit;
            return this;
        }

        public Builder setCreatedFrom(Long createdFrom) {
            this.createdFrom = createdFrom;
            return this;
        }

        public Builder setCreatedTo(Long createdTo) {
            this.createdTo = createdTo;
            return this;
        }

        public Builder setEndingBefore(String endingBefore) {
            this.endingBefore = endingBefore;
            return this;
        }

        public Builder setStartingAfter(String startingAfter) {
            this.startingAfter = startingAfter;
            return this;
        }
    }
}
