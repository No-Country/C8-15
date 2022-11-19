import { motion } from "framer-motion"



const Logo = () => {

  return (
    <motion.div
    animate={{
      rotate: [0, 0, 270, 270, 0],
    }}/>
  );
}

export default Logo