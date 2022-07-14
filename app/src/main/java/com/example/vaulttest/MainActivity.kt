package com.example.vaulttest

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.braintreepayments.api.*
import com.example.vaulttest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PayPalListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var braintreeClient: BraintreeClient
    private lateinit var payPalClient: PayPalClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

/*        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        braintreeClient = BraintreeClient(this, ExampleClientTokenProvider())
        payPalClient = PayPalClient(this, braintreeClient)
//        dataCollector = DataCollector(braintreeClient)
        payPalClient.setListener(this)
    }

    private fun myTokenizePayPalAccountWithVaultMethod() {
        val request = PayPalVaultRequest()
        request.billingAgreementDescription = "Your agreement description"

        payPalClient.tokenizePayPalAccount(this, request)
    }

    override fun onPayPalSuccess(payPalAccountNonce: PayPalAccountNonce) {
        // send payPalAccountNonce.string to server
    }

    override fun onPayPalFailure(error: Exception) {
        if (error is UserCanceledException) {
            // user canceled
        } else {
            // handle error
        }
    }

/*    private fun onPayPalButtonClick(view: View) {
        // The PayPalRequest type will be based on integration type (Checkout vs. Vault)
        payPalClient.tokenizePayPalAccount(this, payPalRequest)
    }
*/
/*    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}