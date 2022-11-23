import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import MoreVertIcon from '@mui/icons-material/MoreVert';

import ButtonCard from './ButtonsCard';


const CardProfile = () => {

const infoCard =[

  {
    avatar:'V',
    name:'Vivian Maier',
    type:'Urbano',
    tittle:'Streat 2',
    date:'19 noviembre, New York, NY',
    descripcion:'Workers on a store',
    url:'https://source.unsplash.com/random/?street'
  },
  {
    avatar:'A',
    name:'Ansel Adams ',
    type:'retratos',
    tittle:'Monte',
    date:'22 noviembre 2022',
    descripcion:'Pinchot pass',
    url:'https://source.unsplash.com/random/?person'
  
  },
  {
    avatar:'D',
    name:'Diane Arbus',
    type:'Naturaleza',
    tittle:'EEUU',
    date:'14 septiembre 2021',
    descripcion:'dos personas',
    url:'https://source.unsplash.com/random/?land'
  }
    
  ];

    return (  
    <>
    { infoCard.map( ({avatar , name , type, tittle , date, descripcion, url }) =>(
    <Card sx={{ width: '320px' }} >
    <CardHeader
      avatar={
        <Avatar aria-label="">
          { avatar }
        </Avatar>
      }
      action={
        <IconButton aria-label="settings">
          <MoreVertIcon />
        </IconButton>
      }
      title={ name }
      subheader= { type }
    />
    <CardMedia
      component="img"
      height="194"
      image= { url } 
      alt="img random"
    />
    <CardContent>
      <Typography variant="body2" color="text.secondary">
        <p>{ tittle } </p>
        <p>{ descripcion }</p>
        <p>{ date }</p>
      </Typography>
    </CardContent>
    <CardActions sx={{
      justifyContent:'flex-end'
    }}>
      <ButtonCard />
    </CardActions>
  </Card>
  ))}
  </>
  );
};

export default CardProfile; 