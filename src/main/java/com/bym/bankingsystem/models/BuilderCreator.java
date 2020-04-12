package com.bym.bankingsystem.models;

public interface BuilderCreator <T extends Builder> {
     T create();
}
