import
  {
    Card,
    CardMedia,
    CardContent,
    CardActions,
    IconButton,
    List, 
    ListItemIcon,
    ListItem, 

  } from '@mui/material'
  import DeleteIcon from '@mui/icons-material/Delete';
  import EditIcon from '@mui/icons-material/Edit';
  import ShareIcon from '@mui/icons-material/Share';
  import QueryBuilderIcon from '@mui/icons-material/QueryBuilder';
  import CameraIcon from '@mui/icons-material/Camera';
  import InfoIcon from '@mui/icons-material/Info';
  import PlaceIcon from '@mui/icons-material/Place';




const CardModal = () =>
{
  return (
    <>
        <Card 
        key=""
        id=""
        relative
          sx={{
          display:'flex', 
          justifyContent:'flex-end',
          maxWidth:900, 
          margin:'80px 250px 350px 260px'}}>
          <CardMedia
            id='1'
            component="img"
            image="https://source.unsplash.com/random?forest'"
            height="700"
            alt='1'
            sx={{
              backgroundSize:'cover',
              backgroundPosition:'center'
            }}
          />
          
          <CardContent sx={{ padding:'10px'}}>
          <List>
                  <ListItem sx={{ fontSize:'21px'}}>
                    Bosque Nativo
                  </ListItem>
                <ListItem>
                  <ListItemIcon>
                    <QueryBuilderIcon />
                    12 noviembre 2015
                  </ListItemIcon>
                  </ListItem>
                  <ListItem>
                  <ListItemIcon>
                    <InfoIcon/>
                    Bosque del sector norte del parque.
                  </ListItemIcon>
                  </ListItem>
                  <ListItem>
                  <ListItemIcon>
                    <PlaceIcon/>
                    Calafate, Argentina.
                  </ListItemIcon>
                  </ListItem>
                  <ListItem>
                  <ListItemIcon>
                    <CameraIcon />
                    Canon R5
                  </ListItemIcon>
                  </ListItem>
          </List>
          </CardContent>
          <CardActions sx={{ display:'flex', justifyContent:'flex-end', padding:'20px'}} >
          <IconButton aria-label="share">
              <ShareIcon/>
            </IconButton>
            <IconButton aria-label="edit" >
              <EditIcon/>
              </IconButton>
            <IconButton aria-label="delete">
              <DeleteIcon/>
            </IconButton>
      </CardActions>
        </Card>
      </>
  )
}

export default CardModal;