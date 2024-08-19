package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiRequestParams;

public class ListParams extends ApiRequestParams {

    @SerializedName("limit")
    private Long limit;

    @SerializedName("starting_after")
    private String startingAfter;

    @SerializedName("ending_before")
    private String endingBefore;

    @SerializedName("created_from")
    private Long createdFrom;

    @SerializedName("created_to")
    private Long createdTo;

    @SerializedName("completed_from")
    private Long completedFrom;

    @SerializedName("completed_to")
    private Long completedTo;

    private ListParams(
            Long limit,
            String startingAfter,
            String endingBefore,
            Long createdFrom,
            Long createdTo,
            Long completedFrom,
            Long completedTo

    ) {
        this.limit = limit;
        this.startingAfter = startingAfter;
        this.endingBefore = endingBefore;
        this.createdFrom = createdFrom;
        this.createdTo = createdTo;
        this.completedFrom = completedFrom;
        this.completedTo = completedTo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long limit;

        private String startingAfter;

        private String endingBefore;

        private Long createdFrom;

        private Long createdTo;

        private Long completedFrom;

        private Long completedTo;

        public ListParams build() {
            return new ListParams(
                    this.limit,
                    this.startingAfter,
                    this.endingBefore,
                    this.createdFrom,
                    this.createdTo,
                    this.completedFrom,
                    this.completedTo
            );
        }

        public Builder setLimit(Long limit) {
            this.limit = limit;
            return this;
        }

        public Builder setStartingAfter(String startingAfter) {
            this.startingAfter = startingAfter;
            return this;
        }

        public Builder setEndingBefore(String endingBefore) {
            this.endingBefore = endingBefore;
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

        public Builder setCompletedFrom(Long completedFrom) {
            this.completedFrom = completedFrom;
            return this;
        }

        public Builder setCompletedTo(Long completedTo) {
            this.completedTo = completedTo;
            return this;
        }

    }
}
