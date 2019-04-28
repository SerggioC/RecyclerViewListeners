//package com.sergiocruz.recyclerviewlisteners;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//public class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.ViewHolder> {
//
//    private final List<Block> blocks;
//    private final List<ImageView> imageViewPool = new LinkedList<>();
//
//    public BlockAdapter(List<Block> blocks) {
//        this.blocks = blocks;
//    }
//
//    @Override
//    public int getItemCount() {
//        return blocks.size();
//    }
//
//    @Override
//    public BlockAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.block_layout, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        holder.bind(blocks.get(position));
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        private final List<ImageView> imageViews = new ArrayList<>();
//        private final ViewGroup container;
//
//        public ViewHolder(View v) {
//            super(v);
//            container = (ViewGroup) itemView;
//        }
//
//        public void bind(Block block) {
//            recycleImageViews();
//            for (int i = 0; i < block.getNumBlocks(); ++i) {
//                final ImageView imageView = getRecycledImageViewOrCreate();
//                imageViews.add(imageView);
//                container.addView(imageView);
//            }
//        }
//
//        private ImageView getRecycledImageViewOrCreate() {
//            if (imageViewPool.isEmpty()) {
//                return (ImageView) LayoutInflater.from(container.getContext()).inflate(R.layout.block, container, false);
//            }
//            return imageViewPool.remove(0);
//        }
//
//        public void recycleImageViews() {
//            imageViewPool.addAll(imageViews);
//            imageViews.clear();
//            container.removeAllViews();
//        }
//    }
//
//    @Override
//    public void onViewRecycled(ViewHolder holder) {
//        super.onViewRecycled(holder);
//        holder.recycleImageViews();
//    }
//}