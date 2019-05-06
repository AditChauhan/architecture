package com.example.beloo.foodnixtest.presentation.producers

import android.os.Bundle
import android.view.View
import com.example.beloo.foodnixtest.R
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.presentation.PresentationActivity
import com.example.beloo.foodnixtest.presentation.util.recyclerView.FactoryAdapter
import com.example.beloo.foodnixtest.presentation.util.recyclerView.RecyclerViewPaginationListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/** Note: i decided to use activity to simplify things,
 * but commonly particular screen is based on fragments
 * and activity is responsible only for flow*/
class ProducersActivity : PresentationActivity(), ProducersContract.View {

    @Inject
    lateinit var presenter: ProducersContract.Presenter

    private val itemAdapter: FactoryAdapter<Producer> =
        FactoryAdapter(ProducerViewHolderFactory())

    private lateinit var paginationListener: RecyclerViewPaginationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val manager = androidx.recyclerview.widget.LinearLayoutManager(
            this@ProducersActivity,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )

        paginationListener = RecyclerViewPaginationListener(
            layoutManager = manager,
            onLoadNext = {
                presenter.onLoadNextPage()
            },
            offsetItemsCount = 5
        )

        rvList.apply {
            layoutManager = manager
            addOnScrollListener(paginationListener)
            adapter = itemAdapter
        }
    }

    override fun setItemsLoadedCompletely() {
        paginationListener.setLoadingFinished()
    }

    override fun setData(producers: List<Producer>) {
        paginationListener.totalItemsCount = producers.size
        vEmpty.visibility = if (producers.isEmpty()) View.VISIBLE else View.GONE
        itemAdapter.removeAll()
        itemAdapter.addAll(producers)
    }

    override fun hideProgress() {
        paginationListener.setIsLoadingNextPage(false)
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

}
