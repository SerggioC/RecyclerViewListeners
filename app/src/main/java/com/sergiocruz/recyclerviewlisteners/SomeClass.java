package com.sergiocruz.recyclerviewlisteners;

public class SomeClass {

    private ActivationInterface listener;

    interface ActivationInterface {
        void onActivateThis(Boolean activate, String what);
    }

    void setInterface(ActivationInterface listener) {
        this.listener = listener;
    }

    void funThing() {
        listener.onActivateThis(true, "Biometrics");
    }

}