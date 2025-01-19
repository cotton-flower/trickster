package dev.enjarai.trickster.spell.blunder;

import dev.enjarai.trickster.Trickster;
import dev.enjarai.trickster.spell.trick.Trick;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class BlockTooHardBlunder extends TrickBlunderException {
    public BlockTooHardBlunder(Trick<?> source) {
        super(source);
    }

    @Override
    public MutableText createMessage() {
        return Text.translatable(Trickster.MOD_ID + ".blunder.block_too_hard");
    }
}
