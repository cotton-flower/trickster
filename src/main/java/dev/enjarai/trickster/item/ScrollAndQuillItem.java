package dev.enjarai.trickster.item;

import dev.enjarai.trickster.SpellTooltipData;
import dev.enjarai.trickster.item.component.ModComponents;
import dev.enjarai.trickster.screen.ScrollAndQuillScreenHandler;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.BundleTooltipData;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class ScrollAndQuillItem extends Item {
    public static BiConsumer<Text, Hand> screenOpener;

    public ScrollAndQuillItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        var otherStack = user.getStackInHand(hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND);
        var slot = hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

        if (user.isSneaking()) {
            if (world.isClient()) {
                screenOpener.accept(Text.of("trickster.screen.sign_scroll"), hand);
            }
        } else {
            user.openHandledScreen(new NamedScreenHandlerFactory() {
                @Override
                public Text getDisplayName() {
                    return Text.translatable("trickster.screen.scroll_and_quill");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new ScrollAndQuillScreenHandler(
                            syncId, playerInventory, stack, otherStack, slot,
                            false, true
                    );
                }
            });
        }

        return TypedActionResult.success(stack);
    }

}
