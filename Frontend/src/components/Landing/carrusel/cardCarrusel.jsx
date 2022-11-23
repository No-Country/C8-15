import { CardMedia , Card } from "@mui/material"
import Carousel from 'react-material-ui-carousel'




const PhotoRandom = [
  {
    url:'https://source.unsplash.com/random/350x300/?bird/'
  },
  {
    url:'https://source.unsplash.com/random/350x300/?foot'  
  },
  {
    url:'https://source.unsplash.com/random/350x300/?person'
  },
  {
    url:'https://source.unsplash.com/random/350x300/?land'
  },
  {
    url:'https://source.unsplash.com/random/350x300/?night'
  }, 
  {
    url:'https://source.unsplash.com/random/350x300/?bird'
  },
  {
    url:'https://source.unsplash.com/random/350x300/?foot'  
  },
  {
    url:'https://source.unsplash.com/random/350x300/?person'
  },
  {
    url:'https://source.unsplash.com/random/350x300/?land'
  },
  {
    url:'https://source.unsplash.com/random/350x300/?night'
  }, 
]

const CardCarrusel = () => {
  return (
    <>
    <Carousel>
    { PhotoRandom.map( ({url}) =>(
    <Card sx={{
      margin:'50px 50px 50px 50px',
      display:'flex',
      justifyContent:'space-around',
      alignItems:'center'

    }}  >
    <CardMedia>
        <img src={ url }/>
    </CardMedia>
  </Card>
  ))}
  </Carousel>
  </>
  )}

export default CardCarrusel