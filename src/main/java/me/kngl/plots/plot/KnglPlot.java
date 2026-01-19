package me.kngl.plots.plot;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class KnglPlot {

    private final @NotNull String id;

    private final @NotNull Location pos1;
    private final @NotNull Location pos2;

    private final @NotNull Set<KnglPlotCoord> coords;

    private @Nullable KnglPlotOwner owner = null;
    private boolean eliminated = false;

    public KnglPlot(@NotNull String id, @NotNull Location pos1, @NotNull Location pos2, @NotNull Set<KnglPlotCoord> coords, @Nullable KnglPlotOwner owner, boolean eliminated) {
        this.id = id;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.coords = coords;
        this.owner = owner;
        this.eliminated = eliminated;
    }

    public @NotNull String id() {
        return this.id;
    }

    public @NotNull Location pos1() {
        return this.pos1.clone();
    }

    public @NotNull Location pos2() {
        return this.pos2.clone();
    }

    public @NotNull Set<KnglPlotCoord> coords() {
        return this.coords;
    }

    public @Nullable KnglPlotOwner owner() {
        return this.owner;
    }

    public void setOwner(@Nullable KnglPlotOwner owner) {
        this.owner = owner;
    }

    public boolean isEliminated() {
        return this.eliminated;
    }

    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }

}