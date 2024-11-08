package xyz.marvhus.beeviewer.mixin;

import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.item.Item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.component.ComponentMap;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
//TODO: Remove when done
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Mixin(BeehiveBlock.class)
public abstract class MixinBeehiveBlock extends BlockWithEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger("BeeViewer"); // WARN: REMOVE WHEN DONE

    protected MixinBeehiveBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType options) {
        super.appendTooltip(stack, context, tooltip, options);

        int count = 0;

        final String BLOCK_ENTITY_TAG = "BlockEntityTag";
        final String BEES = "Bees";
        final byte BEES_HELD_TYPE = NbtElement.COMPOUND_TYPE;
        final int MAX_COUNT = 3;

        if (stack.getComponents() != null) {
            LOGGER.info("[BeeViewer] stack has components:");
            ComponentMap stackComponents = stack.getComponents();
            stackComponents.stream().peek(s -> LOGGER.info("[BeeViewer] components:\n", s.toString()));

            //if (stackComponents.contains(BLOCK_ENTITY_TAG)) {
              //  NbtCompound entityTagsNbt = stackComponents.getCompound(BLOCK_ENTITY_TAG);
                //if (entityTagsNbt.contains(BEES, NbtElement.LIST_TYPE)) {
                  //  NbtList beesListNbt = entityTagsNbt.getList(BEES, BEES_HELD_TYPE);
                    //count = beesListNbt.size();
                //}
            //}
        }
        MutableText text = (MutableText) Text.of("Bees: " + count + "/" + MAX_COUNT);
        text.formatted(Formatting.GRAY);
        tooltip.add(text);
    }
}
