package xyz.marvhus.beeviewer.mixin;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;

@Mixin(BeehiveBlock.class)
public abstract class MixinBeehiveBlock extends BlockWithEntity {

    protected MixinBeehiveBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);

        int count = 0;

        final String BLOCK_ENTITY_TAG = "BlockEntityTag";
        final String BEES = "Bees";
        final byte BEES_HELD_TYPE = NbtElement.COMPOUND_TYPE;
        final int MAX_COUNT = 3;

        if (stack.hasNbt() && stack.getNbt() != null) {
            NbtCompound stackNbt = stack.getNbt();
            if (stackNbt.contains(BLOCK_ENTITY_TAG)) {
                NbtCompound entityTagsNbt = stackNbt.getCompound(BLOCK_ENTITY_TAG);
                if (entityTagsNbt.contains(BEES, NbtElement.LIST_TYPE)) {
                    NbtList beesListNbt = entityTagsNbt.getList(BEES, BEES_HELD_TYPE);
                    count = beesListNbt.size();
                }
            }
        }
        MutableText text = (MutableText) Text.of("Bees: " + count + "/" + MAX_COUNT);
        text.formatted(Formatting.GRAY);
        tooltip.add(text);
    }
}
