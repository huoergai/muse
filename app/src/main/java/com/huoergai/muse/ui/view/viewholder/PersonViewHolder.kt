package com.huoergai.muse.ui.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.textview.MaterialTextView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseViewHolder
import com.huoergai.muse.network.Api
import com.huoergai.muse.persistence.entity.Person

/**
 * D&T: 2023-10-27 16:51
 * DES:
 */
class PersonViewHolder(view: View) : BaseViewHolder(view) {
    private val ivAvatar = view.findViewById<AppCompatImageView>(R.id.iv_avatar)
    private val mtvName = view.findViewById<MaterialTextView>(R.id.mtv_name)

    fun bind(person: Person) {
        mtvName.text = person.name
        person.profile_path?.let {
            ivAvatar.load(Api.buildProfileUrl(it)) { transformations(CircleCropTransformation()) }
        }
    }

}