package io.lokopay.param;

import com.google.gson.annotations.SerializedName;
import io.lokopay.net.ApiRequestParams;

public class ListParams extends ApiRequestParams {

    @SerializedName("limit")
    private Long limit;

    @SerializedName("ending_before")
    private String endingBefore;

    @SerializedName("starting_after")
    private String startingAfter;

    private ListParams(
            Long limit,
            String endingBefore,
            String startingAfter
    ) {
        this.limit = limit;
        this.endingBefore = endingBefore;
        this.startingAfter = startingAfter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long limit;

        private String endingBefore;

        private String startingAfter;

        public ListParams build() {
            return new ListParams(
                    this.limit,
                    this.endingBefore,
                    this.startingAfter
            );
        }

        public Builder setLimit(Long limit) {
            this.limit = limit;
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
