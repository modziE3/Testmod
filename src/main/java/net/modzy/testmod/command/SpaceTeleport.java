package net.modzy.testmod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.command.argument.DimensionArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

import java.util.Collection;

public class SpaceTeleport {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        dispatcher.register(CommandManager.literal("spaceteleport")
                .requires(stack -> stack.hasPermissionLevel(2))
                .then(CommandManager.argument("dimension", DimensionArgumentType.dimension())
                        .executes(SpaceTeleport::teleport)
                        .then(CommandManager.argument("pos", BlockPosArgumentType.blockPos())
                                .executes(SpaceTeleport::teleportToCoords))));
    }

    private static int teleport(CommandContext<ServerCommandSource> context) {
        final int[] retval = new int[]{Command.SINGLE_SUCCESS};
        context.getSource().getServer().execute(() -> {
            ServerPlayerEntity player;
            player = context.getSource().getPlayer();
            try {
                ServerWorld serverWorld = DimensionArgumentType.getDimensionArgument(context, "dimension");
                if (serverWorld == null) {
                    context.getSource().sendError(Text.translatable("commands.spaceteleport.error.cannot_locate_planet_or_moon").setStyle(Style.EMPTY.withColor(Formatting.RED)));
                    retval[0] = -1;
                    return;
                } else if (context.getSource().getWorld().equals(serverWorld)) {
                    context.getSource().sendError(Text.translatable("commands.spaceteleport.error.already_in_dimension").setStyle(Style.EMPTY.withColor(Formatting.RED)));
                    retval[0] = -1;
                    return;
                }
                assert player != null;
                BlockPos pos = getValidTeleportPos(serverWorld, player);
                player.teleport(serverWorld,
                        pos.getX(),
                        pos.getY(),
                        pos.getZ(),
                        player.getYaw(),
                        player.getPitch());
                context.getSource().sendFeedback(() -> Text.translatable("commands.spaceteleport.feedback.single", serverWorld.getDimensionKey().getValue()), true);
            } catch (CommandSyntaxException e) {
                context.getSource().sendError(Text.translatable("commands.spaceteleport.error.already_in_dimension").setStyle(Style.EMPTY.withColor(Formatting.RED)));
                retval[0] = -1;
            }
        });
        return retval[0];
    }

    private static int teleportToCoords(CommandContext<ServerCommandSource> context) {
        final int[] retval = new int[]{Command.SINGLE_SUCCESS};
        context.getSource().getServer().execute(() -> {
            ServerWorld serverWorld;
            BlockPos pos;
            try {
                serverWorld = DimensionArgumentType.getDimensionArgument(context, "dimension");
                pos = BlockPosArgumentType.getValidBlockPos(context, "pos");
                if (serverWorld == null || pos == null) {
                    context.getSource().sendError(Text.translatable("commands.spaceteleport.error.cannot_locate_planet_or_moon").setStyle(Style.EMPTY.withColor(Formatting.RED)));
                    retval[0] = -1;
                    return;
                } else if (context.getSource().getWorld().equals(serverWorld)) {
                    context.getSource().sendError(Text.translatable("commands.spaceteleport.error.already_in_dimension").setStyle(Style.EMPTY.withColor(Formatting.RED)));
                    retval[0] = -1;
                    return;
                }
            } catch (CommandSyntaxException e) {
                context.getSource().sendError(Text.translatable("commands.spaceteleport.error.cannot_locate_planet_or_moon").setStyle(Style.EMPTY.withColor(Formatting.RED)));
                retval[0] = -1;
                return;
            }
            ServerPlayerEntity player = context.getSource().getPlayer();
            assert player != null;
            player.teleport(serverWorld,
                    MathHelper.clamp(pos.getX(), -30000000, 30000000),
                    MathHelper.clamp(pos.getY(), 0, serverWorld.getHeight() - 1),
                    MathHelper.clamp(pos.getZ(), -30000000, 30000000),
                    player.getYaw(),
                    player.getPitch());
            context.getSource().sendFeedback(() -> Text.translatable("commands.spaceteleport.feedback.pos", serverWorld.getDimensionKey().getValue(), pos.getX(), pos.getY(), pos.getZ()), true);
        });
        return retval[0];
    }

    private static BlockPos getValidTeleportPos(ServerWorld world, Entity entity) {
        int posX = (int) entity.getX();
        int posZ = (int) entity.getZ();

        for (int i = world.getHeight(); i > 0; i--) {
            BlockPos.Mutable pos = new BlockPos.Mutable(posX, i, posZ);
            Block currentBlock = world.getBlockState(pos).getBlock();
            if (!currentBlock.getDefaultState().isAir()) {
                pos.setY(pos.getY() + 1);
                return pos;
            }
        }
        return entity.getBlockPos();
    }
}