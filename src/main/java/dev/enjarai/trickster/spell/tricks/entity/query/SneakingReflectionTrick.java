package dev.enjarai.trickster.spell.tricks.entity.query;

import dev.enjarai.trickster.spell.Fragment;
import dev.enjarai.trickster.spell.Pattern;
import dev.enjarai.trickster.spell.SpellContext;
import dev.enjarai.trickster.spell.fragment.BooleanFragment;
import dev.enjarai.trickster.spell.fragment.FragmentType;
import dev.enjarai.trickster.spell.tricks.Trick;
import dev.enjarai.trickster.spell.tricks.blunder.BlunderException;
import dev.enjarai.trickster.spell.tricks.blunder.UnknownEntityBlunder;

import java.util.List;

public class SneakingReflectionTrick extends AbstractLivingEntityQueryTrick {
    public SneakingReflectionTrick() {
        super(Pattern.of(2, 4, 7));
    }

    @Override
    public Fragment activate(SpellContext ctx, List<Fragment> fragments) throws BlunderException {
        return new BooleanFragment(getLivingEntity(ctx, fragments, 0).isSneaking());
    }
}
