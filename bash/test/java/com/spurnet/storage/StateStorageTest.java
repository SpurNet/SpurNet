package com.spurnet.storage;

import com.spurnet.model.State;

public interface StateStorage {
    void saveState(String stateKey, State state) throws Exception;
    State getState(String stateKey) throws Exception;
}
