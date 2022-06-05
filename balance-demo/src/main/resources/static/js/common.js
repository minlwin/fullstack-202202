document.addEventListener('DOMContentLoaded', () => {
	
	document.getElementById('signoutLink').addEventListener('click', () => {
		document.getElementById('signoutForm').submit()
	})
	
	Array.from(document.getElementsByClassName('userStatusChangeLink'))
		.forEach(link => link.addEventListener('click', () => {
			
			document.getElementById('changeUserId').setAttribute('value', link.getAttribute('data-id'))
			document.getElementById('changeUserStatus').setAttribute('value', link.getAttribute('data-status'))
			
			document.getElementById('changeUserName').innerText = link.getAttribute('data-user')
			document.getElementById('changeUserStatusBefore').innerText = link.getAttribute('data-status') == 'true' ? 'Active' : 'Suspend'
			document.getElementById('changeUserStatusAfter').innerText = link.getAttribute('data-status') == 'true' ? 'Suspend' : 'Active'

			const dialog = new bootstrap.Modal('#userStatusChangeModal')
			dialog.show()
		}))
})