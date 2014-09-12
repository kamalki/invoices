class AddtrainIdColumnToReservation < ActiveRecord::Migration
  def change
   add_column :reservations, :train_id, :integer
  end
end
