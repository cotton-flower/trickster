package dev.enjarai.trickster.item;

import dev.enjarai.trickster.item.component.ModComponents;
import dev.enjarai.trickster.screen.ScrollAndQuillScreenHandler;
import dev.enjarai.trickster.spell.SpellContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ScrollAndQuillItem extends Item {
    public ScrollAndQuillItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        var otherStack = user.getStackInHand(hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND);

        if (user.isSneaking()) {
            user.openHandledScreen(new NamedScreenHandlerFactory() {
                @Override
                public Text getDisplayName() {
                    return Text.translatable("trickster.screen.scroll_and_quill");
                }

                @Override
                public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                    return new ScrollAndQuillScreenHandler(syncId, playerInventory, stack, otherStack);
                }
            });
        } else if (!world.isClient()) {
            var spell = stack.get(ModComponents.SPELL);
            if (spell != null) {
                spell.spell().runSafely(new SpellContext((ServerPlayerEntity) user));
            }
        }

        return TypedActionResult.success(stack);
    }
}
