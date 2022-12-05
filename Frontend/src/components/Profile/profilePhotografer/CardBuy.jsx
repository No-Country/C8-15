import
  {
    Card,
    CardMedia,
    CardContent,
    CardActions,
    IconButton,
    Button,
    List, 
    ListItemIcon,
    ListItem
  } from '@mui/material'
  import FavoriteIcon from '@mui/icons-material/Favorite'
  import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
  import theme from '../../../themeConfig'
  import ShareIcon from '@mui/icons-material/Share';
  import QueryBuilderIcon from '@mui/icons-material/QueryBuilder';
  import CameraIcon from '@mui/icons-material/Camera';
  import InfoIcon from '@mui/icons-material/Info';
  import PlaceIcon from '@mui/icons-material/Place';



const CardBuy = () =>
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
          margin:'80px 250px 350px 260px',
          }}>
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
          <IconButton aria-label="add to favorites">
              <FavoriteIcon />
            </IconButton>
            <Button theme={theme} color='primary' href='{LinkMercadoPago}' variant="contained" endIcon={<ShoppingCartIcon/>}>
        Comprar
          </Button>
      </CardActions>
        </Card>
      </>
  )
}

export default CardBuy;     

