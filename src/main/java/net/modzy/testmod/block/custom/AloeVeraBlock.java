package net.modzy.testmod.block.custom;

import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.modzy.testmod.item.ModItems;

public class AloeVeraBlock extends CropBlock {

    public AloeVeraBlock(AbstractBlock.Settings settings) {
            super(settings);}

    @Override
    protected ItemConvertible getSeedsItem () {
        return ModItems.ALOE_VERA;
    }
}
