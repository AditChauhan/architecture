package com.example.beloo.foodnixtest.presentation.producers

import android.os.Bundle
import android.view.View
import com.example.beloo.foodnixtest.R
import com.example.beloo.foodnixtest.data.model.producer.Producer
import com.example.beloo.foodnixtest.presentation.PresentationActivity
import com.example.beloo.foodnixtest.presentation.util.recyclerView.FactoryAdapter
import com.shaubert.ui.adapters.Direction
import com.shaubert.ui.adapters.RecyclerEndlessAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/** Note: i decided to use activity to simplify things,
 * but commonly particular screen is based on fragments
 * and activity is responsible only for flow*/
class ProducersActivity : PresentationActivity(), ProducersContract.View {

    @Inject
    lateinit var presenter: ProducersContract.Presenter

    private lateinit var endlessAdapter: RecyclerEndlessAdapter

    private val itemAdapter: FactoryAdapter<Producer> =
        FactoryAdapter(ProducerViewHolderFactory())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        endlessAdapter = RecyclerEndlessAdapter(itemAdapter).apply {
            pendingResource = R.layout.hidden_progress
            setLoadingCallback { presenter.onLoadNextPage() }
            setEnabled(Direction.END, true)
            setEnabled(Direction.START, false)
            setRemainingPercentOfItemsToStartLoading(0.2f)
        }

        rvList.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                this@ProducersActivity,
                androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
                false
            )
            adapter = itemAdapter
        }
    }

    override fun setItemsLoadedCompletely() {
        endlessAdapter.setAdapterIsFull()
    }

    override fun showError(throwable: Throwable) {
        super.showError(throwable)
        endlessAdapter.onError()
    }

    override fun setData(producers: List<Producer>) {
        vEmpty.visibility = if (producers.isEmpty()) View.VISIBLE else View.GONE
        itemAdapter.removeAll()
        itemAdapter.addAll(producers)
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        endlessAdapter.onDataReady()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

}
