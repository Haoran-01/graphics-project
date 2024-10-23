package GraphicsObjects;



public class Vector4f {

	public float x=0;
	public float y=0;
	public float z=0;
	public float a=0;
	
	public Vector4f() 
	{  
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
		a = 0.0f;
	}
	 
	public Vector4f(float x, float y, float z, float a)
	{ 
		this.x = x;
		this.y = y;
		this.z = z;
		this.a = a;
	}
	
	 //implement Vector plus a Vector 
	public Vector4f PlusVector(Vector4f Additional) {
		Vector4f vector4f = new Vector4f(this.x + Additional.x, this.y + Additional.y, this.z + Additional.z, this.a + Additional.a);
		return vector4f;
		 
	} 
	
	 //implement Vector minus a Vector 
	public Vector4f MinusVector(Vector4f Minus) {
		Vector4f vector4f = new Vector4f(this.x - Minus.x, this.y - Minus.y, this.z - Minus.z, this.a - Minus.a);
		return vector4f;
	}
	
	//implement Vector plus a Point 
	public Point4f PlusPoint(Point4f Additional) {
		Point4f point4f = new Point4f();
		// Add the x,y, and z values of the points to the x,y and z values of the vectors
		point4f.x = this.x + Additional.x;
		point4f.y = this.y + Additional.y;
		point4f.z = this.z + Additional.z;
		point4f.a = this.a + Additional.a;
		return point4f;
	} 
	//Do not implement Vector minus a Point as it is undefined 
	
	//Implement a Vector * Scalar 
	public Vector4f byScalar(float scale ) {
		Vector4f vector4f = new Vector4f(this.x * scale, this.y * scale, this.z * scale, this.a * scale);
		return vector4f;
	}
	
	//implement returning the negative of a  Vector  
	public Vector4f  NegateVector() {
		Vector4f negateVector = new Vector4f( -this.x, -this.y, -this.z, -this.a );
		return negateVector;
	}
	
	//implement getting the length of a Vector  
	public float length() {
		return (float) Math.sqrt( x * x + y * y + z * z + a * a);
	}
	
	//implement getting the Normal  of a Vector  
	public Vector4f Normal() {
		// get the standard vector of the vector
		Vector4f vector4f = new Vector4f();
		vector4f.x = this.x/length();
		vector4f.y = this.y/length();
		vector4f.z = this.z/length();
		vector4f.a = this.a/length();
		return vector4f;
	} 
	
	//implement getting the dot product of Vector.Vector  

	public float dot(Vector4f v)
	{ 
		return ( this.x*v.x + this.y*v.y + this.z*v.z+ this.a*v.a);
	}
	
	// Implemented this for you to avoid confusion 
	// as we will not normally  be using 4 float vector  
	public Vector4f cross(Vector4f v)  
	{ 
    float u0 = (this.y*v.z - z*v.y);
    float u1 = (z*v.x - x*v.z);
    float u2 = (x*v.y - y*v.x);
    float u3 = 0; //ignoring this for now  
    return new Vector4f(u0,u1,u2,u3);
	}
 
}
	 
	   

/*

										MMMM                                        
										MMMMMM                                      
 										MM MMMM                                    
 										MMI  MMMM                                  
 										MMM    MMMM                                
 										MMM      MMMM                              
  										MM        MMMMM                           
  										MMM         MMMMM                         
  										MMM           OMMMM                       
   										MM             .MMMM                     
MMMMMMMMMMMMMMM                        MMM              .MMMM                   
MM   IMMMMMMMMMMMMMMMMMMMMMMMM         MMM                 MMMM                 
MM                  ~MMMMMMMMMMMMMMMMMMMMM                   MMMM               
MM                                  OMMMMM                     MMMMM            
MM                                                               MMMMM          
MM                                                                 MMMMM        
MM                                                                   ~MMMM      
MM                                                                     =MMMM    
MM                                                                        MMMM  
MM                                4 D                                      MMMMMM 
MM                                                                     MMMMMMMM 
MM                                                                  :MMMMMMMM   
MM                                                                MMMMMMMMM     
MM                                                              MMMMMMMMM       
MM                             ,MMMMMMMMMM                    MMMMMMMMM         
MM              IMMMMMMMMMMMMMMMMMMMMMMMMM                  MMMMMMMM            
MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM               ZMMMMMMMM              
MMMMMMMMMMMMMMMMMMMMMMMMMMMMM          MM$             MMMMMMMMM                
MMMMMMMMMMMMMM                       MMM            MMMMMMMMM                  
  									MMM          MMMMMMMM                     
  									MM~       IMMMMMMMM                       
  									MM      DMMMMMMMM                         
 								MMM    MMMMMMMMM                           
 								MMD  MMMMMMMM                              
								MMM MMMMMMMM                                
								MMMMMMMMMM                                  
								MMMMMMMM                                    
  								MMMM                                      
  								MM                                        
                             GlassGiant.com */