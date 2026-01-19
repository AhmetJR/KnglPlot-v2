package me.kngl.plots.plot;

import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class KnglPlotCoord implements Serializable {

    private final String world;
    private final int x;
    private final int z;

    public KnglPlotCoord(Chunk chunk) {
        this(chunk.getWorld().getName(), chunk.getX(), chunk.getZ());
    }

    public KnglPlotCoord(String world, int X, int Z) {
        this.world = world;
        this.x = X;
        this.z = Z;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public String getWorld() {
        return world;
    }

    @Nullable
    public CompletableFuture<Chunk> getChunk() {
        World world = Bukkit.getWorld(this.world);
        if (world == null) return null;
        return PaperLib.getChunkAtAsync(world, this.x, this.z, false);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof KnglPlotCoord) {
            KnglPlotCoord other = (KnglPlotCoord) o;
            return this.world.equals(other.world)
                    && this.x == other.x &&
                    this.z == other.z;
        } else if (o instanceof Chunk) {
            Chunk chunk = (Chunk) o;
            return this.world.equals(chunk.getWorld().getName()) &&
                    this.x == chunk.getX() &&
                    this.z == chunk.getZ();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.world, this.x, this.z);
    }

}
