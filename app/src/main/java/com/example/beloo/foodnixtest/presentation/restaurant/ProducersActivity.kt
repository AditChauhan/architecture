package com.example.beloo.foodnixtest.presentation.restaurant

import android.os.Bundle
import android.view.View
import com.example.beloo.foodnixtest.R
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.presentation.PresentationActivity
import com.example.beloo.foodnixtest.presentation.util.recyclerView.FactoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/** Note: i decided to use activity to simplify things,
 * but commonly particular screen is based on fragments
 * and activity is responsible only for flow*/
class ProducersActivity : PresentationActivity(), ProducersContract.View {

    @Inject
    lateinit var presenter: ProducersContract.Presenter

    private val itemAdapter: FactoryAdapter<Producer> =
        FactoryAdapter(RestaurantViewHolderFactory())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        rvList.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                    this@ProducersActivity,
                    androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                    false
            )
            adapter = itemAdapter
        }
    }

    override fun setData(producers: List<Producer>) {
        vEmpty.visibility = if (producers.isEmpty()) View.VISIBLE else View.GONE
        itemAdapter.removeAll()
        itemAdapter.addAll(producers)
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun setPostCode(postCode: String) {
    }
}
