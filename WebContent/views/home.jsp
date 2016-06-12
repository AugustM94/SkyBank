
<div id="page-wrapper" ng-class="{'open': toggle}" ng-cloak>
	<!-- Sidebar -->
	<div id="sidebar-wrapper">
		<ul class="sidebar">
			<li class="sidebar-main"><a ng-click="toggleSidebar()">
					Dashboard <span class="menu-icon glyphicon glyphicon-transfer"></span>
			</a></li>
			<li class="sidebar-title"><span>NAVIGATION</span></li>
			<li class="sidebar-list"><a href="#">Home<span
					class="menu-icon fa fa-tachometer"></span></a></li>
			<li class="sidebar-list"><a href="#/tables">Transfer<span
					class="menu-icon fa fa-table"></span></a></li>
			<li class="sidebar-list"><a href="#/Manage">Manage<span
					class="menu-icon fa fa-table"></span></a></li>
					<li class="sidebar-list"><a href="#/login">Logout<span
					class="menu-icon fa fa-sign-out"></span>
					</a></li>
	</p>
		</ul>
		<div class="sidebar-footer">
			<div class="col-xs-4">
				<a href="" target="_blank"> About </a>
			</div>
			<div class="col-xs-4">
				<a href="#"> Support </a>
			</div>
		</div>
	</div>
	<!-- End Sidebar -->

	<div class="row header">
		<div class="col-xs-12">
			<div class="meta">
				<div class="page">Dashboard</div>
				<div class="breadcrumb-links">Home</div>
			</div>
		</div>
	</div>
	
	<div class="row">
	<div class="col-lg-6">
		<rd-widget>
			<rd-widget-header icon="fa-users" title="Accounts">
				<!--<input type="text" placeholder="Search" class="form-control input-sm" />-->
			</rd-widget-header>
			<rd-widget-body classes="medium no-padding">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr><th class="text-center">ID</th><th>Account name</th><th>Card</th><th>Balance</th></tr>
						</thead>
						<tbody ng-repeat="account in overview.accounts">
							<tr ><td class="text-center">{{$index+1}}</td><td>{{account.name}}</td><td>{{account.card}}</td><td>{{account.balance}}</td></tr>
						</tbody>
					</table>
        		</div>
			</rd-widget-body>
		<rd-widget>
	</div>
</div>

</div>
<!-- End Page Content -->
