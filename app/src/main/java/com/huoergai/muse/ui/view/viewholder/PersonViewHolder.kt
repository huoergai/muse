package com.huoergai.muse.ui.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.textview.MaterialTextView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseViewHolder
import com.huoergai.muse.model.network.Person
import com.huoergai.muse.network.Api

/**
 * D&T: 2023-10-27 16:51
 * DES:
 */
class PersonViewHolder(view: View) : BaseViewHolder(view) {
    private val ivAvatar = view.findViewById<AppCompatImageView>(R.id.iv_avatar)
    private val mtvName = view.findViewById<MaterialTextView>(R.id.mtv_name)

    fun bind(person: Person) {
        mtvName.text = person.name
        ivAvatar.load(Api.buildProfileUrl(person.profile_path)) {
            transformations(CircleCropTransformation())
        }
    }

}