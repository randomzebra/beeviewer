package xyz.marvhus.beeviewer.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;


@Mixin(BeehiveBlock.class)
public abstract class MixinBeehiveBlock extends BlockWithEntity {

    protected MixinBeehiveBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);

        int count = 0;
        final int MAX_COUNT = 3;
        if (stack.getComponents() != null) {
            List<?> beeList = stack.get(DataComponentTypes.BEES);
            count = beeList.size();
        }
        MutableText text = (MutableText) Text.of("Bees: " + count + "/" + MAX_COUNT);
        text.formatted(Formatting.GRAY);
        tooltip.add(text);
    }
}
