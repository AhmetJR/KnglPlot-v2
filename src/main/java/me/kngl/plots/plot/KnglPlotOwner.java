package me.kngl.plots.plot;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class KnglPlotOwner {

    private final @NotNull UUID id;
    private final @NotNull String name;

    public KnglPlotOwner(@NotNull UUID id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public @NotNull UUID id() {
        return this.id;
    }

    public @NotNull String name() {
        return this.name;
    }

}