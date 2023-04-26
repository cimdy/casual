package com.cimdy.casual.block.custom;

import com.cimdy.casual.item.ItemRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


public class TherapeuticFruitBush extends BushBlock implements FeatureConfiguration {

    private static final VoxelShape SHAPE_0 = makeShape0();
    private static final VoxelShape SHAPE_1 = makeShape1();
    public TherapeuticFruitBush(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
    }

    public static final IntegerProperty AGE = BlockStateProperties.AGE_1;
    public static VoxelShape makeShape0(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.4375, 0, 0.0625, 0.4375, 0.8125, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.1875, 0.5, 0.8125, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.3125, 0.75, 0.8125, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.125, 0.75, 0.125, 0.375, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.4375, 0.3125, 0.4375, 0.9375, 1.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.75, 0.125, 1, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.5625, 0.625, 0.4375, 0.8125, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.5625, 0.75, 0.5625, 0.8125, 0.75), BooleanOp.OR);
        return shape;
    }

    public static VoxelShape makeShape1(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.4375, 0, 0.0625, 0.4375, 0.8125, 0.5), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.0625, 0, 0.1875, 0.5, 0.8125, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0, 0.3125, 0.75, 0.8125, 0.3125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.125, 0.75, 0.125, 0.375, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.4375, 0.3125, 0.4375, 0.9375, 1.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5, 0.75, 0.125, 1, 0.75, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.5625, 0.625, 0.4375, 0.8125, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.5625, 0.75, 0.5625, 0.8125, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 0.4375, 0.75, 0.5625, 0.6875, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0.4375, 0.625, 0.4375, 0.6875, 0.875), BooleanOp.OR);

        return shape;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return new ItemStack(ItemRegister.THERAPEUTIC_FRUIT.get());
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return false;
    }

    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        if (state.getValue(AGE) == 0) {
            return SHAPE_0;
        } else {
            return state.getValue(AGE) < 1 ? SHAPE_1 : super.getShape(state, getter, pos, context);
        }
    }

    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 1;
    }

    protected ItemLike getBerryItem() {
        return ItemRegister.THERAPEUTIC_FRUIT.get();
    }

    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        int i = state.getValue(AGE);
        if (i < 1 && level.getRawBrightness(pos.above(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, source.nextInt(5) == 0)) {
            BlockState blockstate = state.setValue(AGE, Integer.valueOf(i + 1));
            level.setBlock(pos, blockstate, 0);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(blockstate));
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level,pos, state);
        }

    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        return harvestBerry(state, level, pos);
    }

    public InteractionResult harvestBerry(BlockState state, Level level, BlockPos pos) {
        if (isMaxAge(state)) {
            if (level.isClientSide)
                return InteractionResult.SUCCESS;

            level.setBlock(pos, withAge(getMaxAge() - 1), 1);
            popResource(level, pos, new ItemStack(getBerryItem(),  1));
        }

        return InteractionResult.PASS;
    }

    private boolean isMaxAge(BlockState state) {
        return state.getValue(this.getAgeProperty()) >= this.getMaxAge();
    }

    public int getMaxAge() {
        return 1;
    }

    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    public BlockState withAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), Integer.valueOf(age));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}
