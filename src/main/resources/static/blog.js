const handleDelete = id =>  fetch('/blog/' + id, {method: 'DELETE'})
    .then(res => window.location.href = res.url);