
<div id="page-wrapper" ng-class="{'open': toggle}" ng-cloak>
	<!-- Sidebar -->
	<%@include file="./sidebar.jsp" %>
	<!-- End Sidebar -->

	<div class="row header">
		<div class="col-xs-12">
			<div class="meta" style="margin-bottom:20px;">
				<div class="page">Dashboard</div>
				<div class="breadcrumb-links">Home</div>
			</div>
		</div>
	
		<div class="col-lg-4 col-md-12 col-xs-12">
			<div class="widget-container-main widget-container">
				<div class="widget-bubble widget-icon green pull-left">
					<i class="fa fa-users"></i>
				</div>
				<div class="title">{{overview.deposits}}</div>
				<div class="comment">Deposits</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-12 col-xs-12">
			<div class="widget-container-main widget-container">
				<div class="widget-bubble widget-icon orange pull-left">
					<i class="fa fa-users"></i>
				</div>
				<div class="title">{{overview.withdrawels}}</div>
				<div class="comment">Withdrawals</div>
			</div>
		</div>
		<div class="col-lg-4 col-md-12 col-xs-12">
			<div class="widget-container-main widget-container">
				<div class="widget-bubble widget-icon red pull-left">
					<i class="fa fa-tasks"></i>
				</div>
				<div class="title">{{overview.totalBalance}}</div>
				<div class="comment">Total Balance</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-xs-12">
		<h1>Accounts</h1>
			<div class="table-responsive accounts-table widget-container-main">
				<table class="table">
					<thead class="widget-header">
						<tr><th class="text-center">ID</th><th>Account name</th><th>Card</th><th>Balance</th></tr>
					</thead>
					<tbody ng-repeat="account in overview.accounts">
						<tr ng-click="changeAccount($index)"><td class="text-center">{{$index+1}}</td><td>{{account.name}}</td><td>{{account.card}}</td><td>{{account.balance}}</td></tr>
					</tbody>
				</table>
       		</div>
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12">
		<h1>Transactions</h1>
			<div class="table-responsive accounts-table widget-container-main">
				<table class="table">
					<thead class="widget-header">
						<tr><th class="text-center">No.</th><th>Amount</th><th>Description</th></tr>
					</thead>
					<tbody >
						<tr ng-repeat="transaction in transactions"><td class="text-center">{{$index+1}}</td><td>{{transaction.amount}}</td><td>{{transaction.description}}</td></tr>
					</tbody>
				</table>
       		</div>
		</div>
	</div>
</div>
<!-- End Page Content -->
