package com.huoergai.muse.base

import androidx.recyclerview.widget.RecyclerView

/**
 * D&T: 2023-10-12 22:13
 * DES:
 */
abstract class BaseRecyclerViewAdapter<VH : BaseViewHolder> : RecyclerView.Adapter<VH>()