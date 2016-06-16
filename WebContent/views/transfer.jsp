
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
	<div class="row">
		<div class="form-group col-lg-6 col-md-6 col-xs-6">
			<form class="simple-form">
				
				
				<label for="account"> Single select: </label><br>
			    <select class="btn btn-default dropdown-toggle" name="accountid" ng-model="accountid">
			     	<option ng-repeat="account in accounts" value="{{account.id}}">{{account.name}}</option>
			    </select><br>
    
				<label for="regno">Receiver Reg. No.</label>
				<input type="text" name="regno" class="form-control" ng-model="regno" required/><br /> 
				
				<label for="accountno">Receiver Account. No.</label>
				<input type="text" name="accountno" class="form-control" ng-model="accountno" required/><br /> 
				
				<label for="amount">Amount</label>
				<input type="number" name="amount" class="form-control" ng-model="amount" /><br /> 

				<label for="description">Description</label>
				<input type="text" name="description" class="form-control" ng-model="description" required/><br /> 
				
				<div class="form-actions">
					<button type="button" ng-click="reset()" class="btn btn-default">Reset</button> 
					<button type="submit" ng-click="transferFunds()" ng-disabled="form.$invalid" class="btn btn-default">Transfer</button>
			    </div>
			</form>
		</div>	
	</div>
</div>
<!-- End Page Content -->
