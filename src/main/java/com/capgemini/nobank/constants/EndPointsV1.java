package com.capgemini.nobank.constants;

public abstract class EndPointsV1 {

    public static class ACCOUNT {
        public static final String ROOT = "/v1/account";
        public static final String WITHDRAW = "/{id}/withdraw";
        public static final String DEPOSIT = "/{id}/deposit";
        public static final String GET = "/{id}";
    }
}