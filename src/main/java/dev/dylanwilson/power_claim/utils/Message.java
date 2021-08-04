package dev.dylanwilson.power_claim.utils;

public enum Message {
    // Plugin control
    PLUGIN_STARTUP, PLUGIN_SHUTDOWN,

    // Claiming
    NOT_A_PLAYER, CHUNK_CLAIMED, CHUNK_ALREADY_CLAIMED, CHUNK_UNCLAIMED, CHUNK_NOT_CLAIMED,

    // Misc
    UNEXPECTED_ERROR
}
