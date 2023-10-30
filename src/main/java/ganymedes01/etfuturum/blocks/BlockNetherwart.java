package ganymedes01.etfuturum.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ganymedes01.etfuturum.EtFuturum;
import ganymedes01.etfuturum.api.HoeRegistry;
import ganymedes01.etfuturum.client.sound.ModSounds;
import ganymedes01.etfuturum.configuration.configs.ConfigBlocksItems;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

import java.util.List;

public class BlockNetherwart extends BaseSubtypesBlock {

	public BlockNetherwart() {
		super(Material.grass, "nether_wart_block", "warped_wart_block");
		setHardness(1F);
		setResistance(5F);
		setBlockSound(ModSounds.soundWartBlock);
		setNames("wart_block");
		setCreativeTab(EtFuturum.creativeTabBlocks);
		HoeRegistry.addToHoeArray(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		if (ConfigBlocksItems.enableNetherwartBlock) {
			list.add(new ItemStack(item, 1, 0));
		}
		if (ConfigBlocksItems.enableWarpedBlocks) {
			list.add(new ItemStack(item, 1, 1));
		}
	}

	@Override
	public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
		//ARGH I WANT TO ONLY PREVENT CERTAIN NETHER MOBS BUT THIS FUNCTION STUPIDLY LACKS THE ACTUAL DAMN ENTITY BEING PASSED IN! CURSE YOU FORGE!
		return type != EnumCreatureType.monster && world.getBlockMetadata(x, y, z) == 0;
	}
}
