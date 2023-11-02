package com.huoergai.muse.ui.people

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import coil.load
import coil.transform.CircleCropTransformation
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseActivity
import com.huoergai.muse.databinding.ActivityPersonDetailBinding
import com.huoergai.muse.extension.enableTransition
import com.huoergai.muse.model.network.Person
import com.huoergai.muse.network.Api
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * D&T: 2023-10-27 17:52
 * DES:
 */
@AndroidEntryPoint
class PersonDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityPersonDetailBinding
    private val vm: PeopleDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableTransition()

        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val personID = intent.getIntExtra(EXTRA_PERSON_ID, -1)
        val personName = intent.getStringExtra(EXTRA_PERSON_NAME)
        binding.mtvName.text = personName

        vm.loadPersonDetail(personID)
        ViewCompat.setTransitionName(findViewById(android.R.id.content), personName)

        setupActionBar()

        lifecycleScope.launch {
            vm.personDetail.collect { p ->
                p?.let {
                    binding.ivAvatar.load(Api.buildProfileUrl(it.profile_path)) {
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }

    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.outline_arrow_back_24)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val EXTRA_PERSON_ID = "_extra:person:id"
        private const val EXTRA_PERSON_NAME = "_extra:person:name"

        fun start(activity: Activity, share: View, person: Person) {
            val intent = Intent(activity, PersonDetailActivity::class.java)
            intent.putExtra(EXTRA_PERSON_ID, person.id)
            intent.putExtra(EXTRA_PERSON_NAME, person.name)
            val option =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, share, person.name)
            activity.startActivity(intent, option.toBundle())
        }

    }

}